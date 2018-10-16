const prod = require('../../webpack.prod.js');

module.exports = env =>
  prod(env, {
    project: 'context',
    appId: 'app',
    __dirbase: __dirname,
  });
