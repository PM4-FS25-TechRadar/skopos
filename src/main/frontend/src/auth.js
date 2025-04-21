import Keycloak from 'keycloak-js'

const keycloak = new Keycloak({
    url: 'https://kc-skopos.pm4.init-lab.ch/',
    realm: 'quarkus',
    clientId: 'frontend-app',
})

export default keycloak