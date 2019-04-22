const path = require('path');
const webpack = require('webpack');

const isProd = process.env.NODE_ENV === 'production'
module.exports = {
    devtool: isProd? false : '#cheap-module-source-map',
    
    output:{
        path : path.join(__dirname,'../dist'),
        filename:'js/[name]-[chunkhash:8].js',
        chunkFilename :'js/[name]-[chunkhash:8].js',
        publicPath:'/static/'
    },
    resolve: {
        extensions: ['.js', '.vue', '.json'],
        alias: {
            '@': path.join(__dirname,'../src'),
            '@images': path.join(__dirname,'../src/assets/images'),
        }
    },
    //如果一个资源超过 300kb，webpack 会对此输出一个警告来通知你。
    performance: {
        maxEntrypointSize: 300000,
        hints: isProd ? 'warning' : false
    },
    module:{
        rules:[
            {
                test: /\.vue$/,
                loader: 'vue-loader'
            },
            
            {
                test: /\.js$/,
                exclude: /(node_modules)/,
                use: {
                    loader: 'babel-loader',
                    options: {
                        presets: ['env'],
                        plugins: [require('babel-plugin-transform-object-rest-spread'),'transform-runtime',"syntax-dynamic-import"]
                    }
                }
            },
            {
                test: /\.(png|jpe?g|gif|svg)(\?.*)?$/,
                loader: 'url-loader',
                options: {
                    limit: 10000,
                    name: 'img/[name]-[hash:7].[ext]'
                }
            },
            {
                test: /\.(mp4|webm|ogg|mp3|wav|flac|aac)(\?.*)?$/,
                loader: 'url-loader',
                options: {
                    limit: 10000,
                    name: 'media/[name]-[hash:7].[ext]'
                }
            },
            {
                test: /\.(woff2?|eot|ttf|otf)(\?.*)?$/,
                loader: 'url-loader',
                options: {
                    limit: 10000,
                    name: 'fonts/[name]-[hash:7].[ext]'
                }
            }
        ]
    },
    plugins:[
        new webpack.ProvidePlugin({
            jQuery: "jquery",
            $: "jquery"
        })
    ]
}