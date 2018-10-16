const dev = require('../../webpack.dev.js');

module.exports = env =>
  dev(env, {
    project: 'context',
    appId: 'app',
    __dirbase: __dirname,
  });
