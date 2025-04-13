import { createApp } from 'vue'
import './assets/app.css'
import App from './App.vue'
import keycloak from './auth'

keycloak.init({ onLoad: 'login-required' }).then(authenticated => {
    if (authenticated) {
        const app = createApp(App)
        app.provide('keycloak', keycloak)
        app.mount('#app')
    } else {
        console.warn('User not authenticated')
        window.location.reload()
    }
}).catch((err) => {
    console.error('Keycloak initialisation failed', err)
})
