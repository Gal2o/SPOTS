<template>
  <div class="header" :class="{[`bg-${type}`]: type}" style="background-image:url('img/background/goal.jpg'); 
    background-repeat:no-repeat;
    background-size:cover;" >
    <div class="container-fluid" style="width:1200px; height:110px;">
      <div class="header-body">
        <slot></slot>
      </div>
    </div>
  </div>
</template>
<script>
  export default {
    name: 'base-header',
    props: {
      type: {
        type: String,
        default: 'primary',
        description: 'Header background type'
      }
    }
  }
</script>
<style>
</style>
