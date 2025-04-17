export function deepClone (obj) {
    try {
        return structuredClone(obj)
    } catch { // unsupported or contains proxies
        return JSON.parse(JSON.stringify(obj))
    }
}