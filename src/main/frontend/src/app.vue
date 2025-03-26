<template>
  <div class="app-layout" :class="{ collapsed: isSidebarCollapsed }">
    <sidebar :activeView="activeView" @navigate="activeView = $event" @toggle="isSidebarCollapsed = $event" />
    <div class="main-content">
      <radar v-if="activeView === 'radar'" />
      <div v-else-if="activeView === 'entries'">Radar Entry List (coming soon)</div>
      <div v-else-if="activeView === 'about'">About Page (coming soon)</div>
    </div>
  </div>
</template>

<script>
import sidebar from './components/sidebar/sidebar.vue'
import radar from './components/radar/radar.vue'

export default {
  components: {
    sidebar,
    radar
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
  margin-left: 220px; /* width of sidebar */
  overflow-y: auto;
  height: 100vh;
}
</style>
