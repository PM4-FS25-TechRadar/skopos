<template>
  <div :class="['sidebar', { collapsed: isCollapsed }]">
    <button class="toggle-btn" @click="toggleSidebar">
      {{ isCollapsed ? '☰' : '✕' }}
    </button>
    <div v-if="!isCollapsed" class="sidebar-nav">
      <ul>
        <li :class="{ active: activeView === 'radar' }" @click="$emit('navigate', 'radar')">Radar</li>
        <li :class="{ active: activeView === 'technologies' }" @click="$emit('navigate', 'technologies')">Technologies</li>
        <li :class="{ active: activeView === 'entries' }" @click="$emit('navigate', 'entries')">Entries</li>
        <li :class="{ active: activeView === 'about' }" @click="$emit('navigate', 'about')">About</li>
      </ul>
    </div>
  </div>
</template>

<script>
import './sidebar.css'

export default {
  props: ['activeView'],
  data() {
    return {
      isCollapsed: false
    }
  },
  methods: {
    toggleSidebar() {
      this.isCollapsed = !this.isCollapsed
      this.$emit('toggle', this.isCollapsed)
    }
  }
}
</script>
