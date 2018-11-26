const merge = require('webpack-merge');
const baseConfig = require('./webpack.base.config.js');
const path = require('path');
const webpack = require('webpack')
const VueLoaderPlugin = require('vue-loader/lib/plugin')
const VueSSRServerPlugin = require('vue-server-renderer/server-plugin')

const isProd = process.env.NODE_ENV === 'production'

const config = merge(baseConfig,{
    mode:isProd?'production':'development',
    entry:{
        app:path.join(__dirname,'../src/entry-server.js'),
        //vendors:['vue','vue-router','vuex']
    },
    target: 'node',

    // For bundle renderer source map support
    devtool: 'source-map',
    resolve: {
        extensions: ['.js', '.vue', '.json'],
        alias: {
            'create-api': path.join(__dirname, '../src/api/create-api-server.js')
        }
    },
    // This tells the server bundle to use Node-style exports
    output: {
        libraryTarget: 'commonjs2'
    },
    externals: Object.keys(require('../package.json').dependencies),
    
    plugins:[
        new VueLoaderPlugin(),
        new webpack.DefinePlugin({
            'process.env.NODE_ENV': JSON.stringify(process.env.NODE_ENV || 'development'),
            'process.env.VUE_ENV': '"server"'
        }),
        new VueSSRServerPlugin()
    ],
    
});
if(isProd){
    config.module.rules.push(
        {
            test: /\.less$/,
            use: [
                'css-loader',
                'postcss-loader',
                'less-loader'
            ]
        },
        {
            test: /\.css$/,
            use: [
                'css-loader',
                'postcss-loader'
            ]
        }
    )
   
}else{
    config.module.rules.push(
        {
            test: /\.less$/,
            use: [
                'vue-style-loader',
                'css-loader',
                'postcss-loader',
                'less-loader'
            ]
        },
        {
            test: /\.css$/,
            use: [
                'vue-style-loader',
                'css-loader',
                'postcss-loader'
            ]
        }
    )
    
}
module.exports = config;