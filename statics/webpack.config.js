const path = require('path'); // node.js 내장 패키지
const MiniCssExtractPlugin = require("mini-css-extract-plugin");
const FileManagerPlugin = require('filemanager-webpack-plugin');

module.exports = {
    entry: {
      bundle: './src/index.js',
    },
    output: {
        filename: '[name].js?hash=[hash]',
        path: path.resolve(__dirname, 'dist'),
    },

    externals: {
      jquery: 'jQuery',
    },

    plugins: [
      new FileManagerPlugin({
        onStart: {
          delete: [
            path.join(__dirname, 'dist'),
            // path.join(__dirname, '../src/main/resources/META-INF/resources/dist'),
          ],
        },
        /*onEnd: {
          copy: [
            { source: path.join(__dirname, 'dist'), destination: path.join(__dirname, '../src/main/resources/META-INF/resources/dist') },
          ],
        }*/
      }),
      new MiniCssExtractPlugin({
        filename: "[name].css",
        chunkFilename: "[id].css"
      }),
    ],

    module: {
        rules: [
            {
                test: /\.css$/,
                use: [
                  {
                    loader: MiniCssExtractPlugin.loader,
                    options: {
                      // you can specify a publicPath here
                      // by default it use publicPath in webpackOptions.output
                      publicPath: '../'
                    }
                  },
                  "css-loader"
                ],
            },
            {
                test: /\.(png|jpg|gif)$/,
                use: ['file-loader'],
            },

            {
             test: /.(ttf|otf|eot|svg|woff(2)?)(\?[a-z0-9]+)?$/,
             use: [{
               loader: 'file-loader',
               options: {
                 name: '[name].[ext]',
                 outputPath: 'fonts/',    // where the fonts will go
                 publicPath: '../'       // override the default path
               }
             }]
           },
        ],
    },
};