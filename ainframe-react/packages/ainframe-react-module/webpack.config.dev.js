const path = require('path'); // node.js 내장 패키지
const dev = require('../../webpack.dev.js');

module.exports = env =>
  dev(
    env,
    {
      project: 'module',
      appId: 'app',
      __dirbase: path.resolve(__dirname, ''),
    },
    {
      devServer: {
        proxy: {
          '/api': {
            target: 'http://localhost:8888',
            // pathRewrite: { [`^${apiPath}`]: '' }, // proxy path 를 제거하도록 다시 쓴다.
            secure: false,
            prependPath: true,
          },
        },
      },
    },
  );
