import Keycloak from 'keycloak-js'

const keycloak = new Keycloak({
    url: 'http://localhost:9000/',
    realm: 'quarkus',
    clientId: 'frontend-app',
})

export default keycloak