const webpack = require('webpack');
const fs = require('fs');
var path = require('path');

var options = {
    entry: './src/main/js/app.js',
    devtool: 'source-map',
    cache: true,
    debug: true,
    output: {
        path: __dirname,
        filename: './src/main/resources/static/built/bundle.js'
    },
    module: {
        loaders: [
            {
                test: path.join(__dirname, '.'),
                exclude: /(node_modules)/,
                loader: 'babel',
                query: {
                    cacheDirectory: true,
                    presets: ['es2015', 'react']
                }
            }
        ]
    }
};

fs.watch('src/main/js', function (e, file) {
    console.log('event is: ' + e);
    console.log('file is: ' + file);
    var compiler = webpack(options);
    compiler.run(function(err, stats) {
        console.log(err);
        console.log(stats);
    });
});
