<template>
    <div class="navbar">
        nav
    </div>
  </template>

  <script>
  export default {
    name: 'AppMain',
    computed: {
      key() {
        return this.$route.fullPath
      }
    }
  }
</script>