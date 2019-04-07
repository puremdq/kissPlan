const merge = require('webpack-merge');
const baseConfig = require('./webpack.base.config.js');
const htmlWebpackPlugin = require('html-webpack-plugin');
const UglifyJsPlugin = require("uglifyjs-webpack-plugin");
const OptimizeCSSAssetsPlugin = require("optimize-css-assets-webpack-plugin");
const path = require('path');
const webpack = require('webpack');
const VueSSRClientPlugin = require('vue-server-renderer/client-plugin')
const VueLoaderPlugin = require('vue-loader/lib/plugin')
const MiniCssExtractPlugin = require('mini-css-extract-plugin');
const WebpackMd5Hash = require('webpack-md5-hash');

const isProd = process.env.NODE_ENV === 'production'

const config = merge(baseConfig,{
    mode:isProd?'production':'development',
    entry:{
        app:path.join(__dirname,'../src/entry-client.js'),
        //vendors:['vue','vue-router','vuex']
    },
    resolve: {
        extensions: ['.js', '.vue', '.json'],
        alias: {
            //'vue$':  'vue/dist/vue.common.js',
            '@': path.join(__dirname,'../src'),
            'create-api': path.join(__dirname, '../src/api/create-api-client.js')
        }
    },
    output:{
        path : path.join(__dirname,'../dist'),
        filename: isProd ? 'js/[name]-[chunkhash].js' : 'js/[name].js',
        chunkFilename :isProd ? 'js/[name]-[chunkhash].js':'js/[name].js',
        publicPath:'/static/'
    },
    plugins:[
        new VueLoaderPlugin(),
        new htmlWebpackPlugin({
            template:path.join(__dirname,'../src/index.template.html')
        }),
        new webpack.DefinePlugin({
            'process.env.NODE_ENV': JSON.stringify(process.env.NODE_ENV || 'development'),
            'process.env.VUE_ENV': '"client"'
        }),
        new VueSSRClientPlugin()
    ]
    
});
if(isProd){
    config.entry.vendors = ['vue','vue-router','vuex'];
    config.optimization= {
        splitChunks: {
            chunks: "all", // 必须三选一： "initial" | "all"(默认就是all) | "async"
            minSize: 30000,
            automaticNameDelimiter: '~',
            name: true,
            cacheGroups: {
                vendors: {
                    name: "vendors",
                    chunks: "initial",
                    minChunks: 2
                }
            } 
           
        },
        runtimeChunk : {
            name: 'manifest'
        }
    }
    config.plugins.push(
        new UglifyJsPlugin({
            cache: true,
            parallel: true,
            sourceMap: true // set to true if you want JS source maps
        }),
        new OptimizeCSSAssetsPlugin({}),
        new MiniCssExtractPlugin({
            filename: "css/[name].[chunkhash:6].css",
            chunkFilename: "css/[name].[chunkhash:6].css"
        }),
        new WebpackMd5Hash(),//使用使用webpack-md5-hash解耦css和js文件hash值 
    )
    config.module.rules.push(
        {
            test: /\.less$/,
            use: [
                MiniCssExtractPlugin.loader,
                'css-loader',
                'postcss-loader',
                'less-loader'
            ]
        },
        {
            test: /\.css$/,
            use: [
                MiniCssExtractPlugin.loader,
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