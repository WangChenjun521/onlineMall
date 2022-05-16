module.exports = {
    publicPath: "./",
    outputDir: "dist",
    assetsDir: "assets",
    indexPath: "index.html",
    devServer: {
        proxy: {
            '/admin': {
                target: 'gateway:8085',
                ws: true,
                changeOrigin: true
            },
        }
    }
}