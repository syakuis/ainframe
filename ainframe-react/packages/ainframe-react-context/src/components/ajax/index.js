import axios from 'axios';

const defaultData = {
  error: false,
  message: null,
  code: 200,
  statusCode: null,
  redirectUrl: null,
};

let defaultConfig = {
  prepare: undefined,
  success: undefined,
  error: undefined,
  done: undefined,
};

const ajaxConfig = props => {
  defaultConfig = Object.assign({}, defaultConfig, props);
};

const responseData = (res, isRedirect, defaultRedirectUrl) => {
  const data = res.data || defaultData;
  const redirectUrl = isRedirect ? data.redirectUrl || defaultRedirectUrl : null;

  return {
    status: res.status || 200,
    error: data.error || false,
    message: data.message || null,
    code: data.code || 200,
    statusCode: data.statusCode || null,
    redirectUrl,
  };
};

const request = (method, url, props, success, faliure, done) => {
  const properties = Object.assign(
    {},
    {
      isMessage: true,
      isAlwaysFinally: false,
      isRedirect: true,
      defaultRedirectUrl: null,
    },
    props,
  );

  const { isMessage, isAlwaysFinally, isRedirect, defaultRedirectUrl, ...config } = properties;

  let state = null;

  if (typeof defaultConfig.prepare === 'function') defaultConfig.prepare(properties);

  axios({
    method,
    url,
    ...config,
  })
    .then(res => {
      state = responseData(res, isRedirect, defaultRedirectUrl);

      let next = null;
      if (typeof success === 'function') {
        next = success(res, state);
      }

      if (next !== false && typeof defaultConfig.success === 'function')
        defaultConfig.success(state, res, properties);
    })
    .catch(error => {
      state = responseData(error.response, isRedirect, defaultRedirectUrl);

      let next = null;
      if (typeof faliure === 'function') {
        next = faliure(error, state);
      }

      if (next !== false && typeof defaultConfig.error === 'function')
        defaultConfig.error(state, error, properties);
    })
    .then(() => {
      let next = null;
      // Redirect 가 호출되면 unmount 시점이기 때문에 finally 를 호출하지 않는 다.
      // 단 isAlwaysFinally = true 로 설정하면 무조건 호출한다.
      if (
        typeof done === 'function' &&
        (isAlwaysFinally || !(state.code > 400 && state.code < 407))
      ) {
        next = done(state);
      }

      if (next !== false && typeof defaultConfig.done === 'function')
        defaultConfig.done(state, properties);
    });
};

const ajax = {
  get: (url, config, success, faliure, done) => request('get', url, config, success, faliure, done),
  post: (url, config, success, faliure, done) =>
    request('post', url, config, success, faliure, done),
  put: (url, config, success, faliure, done) => request('put', url, config, success, faliure, done),
  delete: (url, config, success, faliure, done) =>
    request('delete', url, config, success, faliure, done),
};

export default ajax;
export { ajaxConfig, responseData };
