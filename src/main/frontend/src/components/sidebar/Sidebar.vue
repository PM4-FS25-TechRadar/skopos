<template>
  <div :class="['sidebar', { collapsed: isCollapsed }]">
    <button class="toggle-btn" @click="toggleSidebar">
      {{ isCollapsed ? '☰' : '✕' }}
    </button>
    <div v-if="!isCollapsed" class="sidebar-nav">
      <ul>
        <router-link to="/">
          <li :class="{ active: $route.name === 'radars' }">Radars</li>
        </router-link>
        <router-link to="/technologies">
          <li :class="{ active: $route.name === 'technologies' }">Technologies</li>
        </router-link>
<!--        <li :class="{ active: activeView === 'entries' }" @click="$emit('navigate', 'entries')">Entries</li>-->
<!--        <li :class="{ active: activeView === 'about' }" @click="$emit('navigate', 'about')">About</li>-->
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
