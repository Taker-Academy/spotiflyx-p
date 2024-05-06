<script lang="ts">
    import SideScrollMenu from '$lib/SideScrollMenu.svelte';
    import Title from '$lib/Title.svelte';
    import '../styles/global.css';
</script>

<Title></Title>
<div class="left-bar">
    <SideScrollMenu />
</div>

<style>
    .left-bar {
        height: 100vh;
        width: 200px;
    }
</style>