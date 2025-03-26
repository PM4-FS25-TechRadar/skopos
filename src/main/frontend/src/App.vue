<template>
  <div :class="{ collapsed: isSidebarCollapsed }" class="app-layout">
    <Sidebar :activeView="activeView" @navigate="activeView = $event" @toggle="isSidebarCollapsed = $event"/>
    <div class="main-content">
      <Radar v-if="activeView === 'radar'"/>
      <RadarEntriesList v-else-if="activeView === 'entries'"/>
      <div v-else-if="activeView === 'about'">About Page (coming soon)</div>
    </div>
  </div>
</template>

<script>
import Sidebar from './components/sidebar/Sidebar.vue'
import Radar from './components/radar/Radar.vue'
import RadarEntriesList from './components/radar-entries/RadarEntriesList.vue'

export default {
  components: {
    Sidebar,
    Radar,
    RadarEntriesList
  },
  data() {
    return {
      activeView: 'radar',
      isSidebarCollapsed: false
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
