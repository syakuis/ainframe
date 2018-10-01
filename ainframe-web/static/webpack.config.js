const path = require('path'); // node.js 내장 패키지
const ExtractTextPlugin = require('extract-text-webpack-plugin');

module.exports = {
    entry: './src/index.js',
    output: {
        filename: '[name].js?hash=[hash]',
        path: path.resolve(__dirname, 'dist'),
    },

    plugins: [
        new ExtractTextPlugin('[name].css?hash=[hash]'),
    ],

    module: {
        rules: [
            {
                test: /\.css$/,
                use: ExtractTextPlugin.extract({
                    fallback: 'style-loader', use: 'css-loader',
                }),
            },
            {
                test: /\.(png|jpg|gif)$/,
                use: ['file-loader'],
            },
            {
                test: /\.(eot|svg|ttf|woff|woff2)$/,
                use: ['file-loader'],
            },
        ],
    },
};