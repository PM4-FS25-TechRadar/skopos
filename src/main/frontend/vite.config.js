import {defineConfig} from 'vite'
import vue from '@vitejs/plugin-vue'
import path from 'path'

export default defineConfig({
    plugins: [vue()],
    server: {
        proxy: {
            '/radar': {
                target: 'http://localhost:8080',
                changeOrigin: true,
                secure: false
            },
            '/technologies': {
                target: 'http://localhost:8080',
                changeOrigin: true,
                secure: false
            }
        }
    },
    build: {
        outDir: '../resources/META-INF/resources',
        emptyOutDir: true
    },
    resolve: {
        alias: {
            '@': path.resolve(__dirname, './src'),
        },
    },
    css: {
        devSourcemap: true
    },
})