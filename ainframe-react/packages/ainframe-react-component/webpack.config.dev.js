const path = require('path'); // node.js 내장 패키지
const dev = require('../../webpack.dev.js');

module.exports = env =>
  dev(
    env,
    {
      project: 'component',
      appId: 'app',
      __dirbase: path.resolve(__dirname, ''),
    },
    {
      entry: {
        index: './src/containsers/dev.js',
      },
    },
  );
