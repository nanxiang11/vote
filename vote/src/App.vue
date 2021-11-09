<template>
  <div id="nav">

  </div>
  <router-view/>
  <Footer/>
</template>

<style>

</style>

<script>
import Footer from "./components/Footer";

export default {
  name: "app",
  components:{
    Footer
  }
}
</script>
