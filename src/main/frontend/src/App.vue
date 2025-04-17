<template>
  <div :class="{ collapsed: isSidebarCollapsed }" class="app-layout">
    <Sidebar :activeView="activeView" @navigate="activeView = $event" @toggle="isSidebarCollapsed = $event"/>
    <div class="main-content">
      <div>
        <div>👤 {{ username }}</div>
        <button @click="logout">Logout</button>
      </div>
      <Radar v-if="activeView === 'radar'"/>
      <RadarsList v-else-if="activeView === 'radars'"/>
      <RadarEntriesList v-else-if="activeView === 'entries'"/>
      <TechnologyList v-else-if="activeView === 'technologies'"/>
      <div v-else-if="activeView === 'about'">About Page (coming soon)</div>
    </div>
  </div>
</template>

<script>
import { inject } from 'vue'
import Sidebar from './components/sidebar/Sidebar.vue'
import Radar from './components/radar/Radar.vue'
import RadarsList from './components/radars/RadarsList.vue'
import RadarEntriesList from './components/radar-entries/RadarEntriesList.vue'
import TechnologyList from './components/technologies/TechnologyList.vue'

export default {
  name: 'App',
  components: {
    Sidebar,
    Radar,
    RadarsList,
    RadarEntriesList,
    TechnologyList
  },
  inject: ['keycloak'],
  data() {
    return {
      activeView: 'radar',
      isSidebarCollapsed: false
    }
  },
  computed: {
    username() {
      return this.keycloak?.tokenParsed?.preferred_username || 'Unknown'
    }
  },
  methods: {
    logout() {
      this.keycloak.logout({ redirectUri: window.location.origin })
    }
  }
}
</script>

<style>
.app-layout {
  display: flex;
}

.main-content {
  flex: 1;
  padding: 2rem;
  margin-left: 220px;
  overflow-y: auto;
  height: 100vh;
}
</style>
