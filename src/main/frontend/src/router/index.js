import { createRouter, createWebHistory } from 'vue-router'
import RadarsList from '@/components/radars/RadarsList.vue'
import RadarView    from '@/components/radar/Radar.vue'
import technologyList from "@/components/technologies/TechnologyList.vue";

const routes = [
    { path: '/', name: 'radars', component: RadarsList },
    { path: '/radar/:id', name: 'radar', component: RadarView },
    { path: '/technologies', name: 'technologies', component: technologyList}
]

export default createRouter({
    history: createWebHistory(),
    routes
})
