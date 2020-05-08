const path = require('path')
// ini diatas(ES5) sama aja kaya import path from 'path'(ES6)
// node blom bisa baca import

const rules = [
    {
        // smua file yg end dengan tsx
        // kecuali dari file node_module
        // pake babel loader untuk load
        test: /\.tsx?/,
        exclude: /node_module/,
        loader: 'babel-loader'
    }
]

module.exports = {
    target: 'web',
    mode:'development',
    entry:'./src/index.tsx', //main code 
    output:{
        // untuk kasih tau tempat exported, compiled code nya kemana, ke folder build di root dir
        path: path.resolve(__dirname,'build'),
        filename:"bundle.js", // nama file di index.html
        publicPath: '/'
    },
    module: {rules}, // {rules} sama kaya {rules:rules}
    // resolve extension file wktu import
    resolve:{extensions: ['.ts','.tsx','.js']},
    devServer:{
        contentBase: './', // ambil content dari root, export ke port 5000
        port: 5001,
        historyApiFallback: true
    }
}