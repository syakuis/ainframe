const path = require('path');
const webpack = require('webpack');
const MiniCssExtractPlugin = require('mini-css-extract-plugin');
const FileManagerPlugin = require('filemanager-webpack-plugin');

module.exports = (env, args) => {
  const { appId, __dirbase } = args;
  const publicPath = '/';

  let dirbase = __dirbase;
  if (!__dirbase) {
    dirbase = __dirname;
  }

  if (!publicPath || publicPath === '') {
    throw new Error('publicPath must not be null');
  }

  if (!appId || appId === '') {
    throw new Error('appId must not be null');
  }

  if (!dirbase || dirbase === '') {
    throw new Error('dirbase must not be null');
  }

  return {
    output: {
      path: path.join(dirbase, 'dist'),
      publicPath,
      filename: '[name].js?hash=[hash]',
    },

    plugins: [
      new webpack.DefinePlugin({
        APP_ID: JSON.stringify(appId),
      }),
      new FileManagerPlugin({
        onStart: {
          delete: [
            path.join(dirbase, 'dist'),
            // path.join(__dirname, 'resources/dist'),
          ],
        },
        // onEnd: {
        //   copy: [
        //     {
        //       source: path.join(__dirname, 'dist'),
        //       destination: path.join(__dirname, 'resources/dist'),
        //     },
        //   ],
        // },
      }),
      new MiniCssExtractPlugin({
        filename: '[name].css',
        chunkFilename: '[id].css',
      }),
    ],

    module: {
      rules: [
        {
          test: /\.js$/,
          enforce: 'pre',
          loader: 'eslint-loader',
          include: path.join(dirbase, 'src'),
        },
        {
          test: /\.m?js$/,
          include: path.join(dirbase, 'src'),
          use: {
            loader: 'babel-loader',
            options: {
              cacheDirectory: true,
            },
          },
        },
        {
          test: /\.css$/,
          use: [
            {
              loader: MiniCssExtractPlugin.loader,
              options: {
                // you can specify a publicPath here
                // by default it use publicPath in webpackOptions.output
                publicPath,
              },
            },
            {
              loader: 'css-loader',
            },
          ],
        },
        {
          test: /.(png|jpg|gif)(\?[a-z0-9]+)?$/,
          use: {
            loader: 'file-loader',
            options: {
              name: '[name].[ext]',
              outputPath: 'images/',
              publicPath: `${publicPath}images/`,
            },
          },
        },

        {
          test: /.(ttf|otf|eot|svg|woff(2)?)(\?[a-z0-9]+)?$/,
          use: [
            {
              loader: 'file-loader',
              options: {
                name: '[name].[ext]',
                outputPath: 'fonts/',
                publicPath: `${publicPath}fonts/`,
              },
            },
          ],
        },
      ],
    },

    resolve: {
      alias: {
        _src: path.resolve(dirbase, 'src'),
        _components: path.resolve(dirbase, 'src/components'),
      },
    },
  };
};
