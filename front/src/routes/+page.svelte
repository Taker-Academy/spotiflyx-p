<script lang="ts">
	import SearchResult from '$lib/SearchResult.svelte';
    import SideScrollMenu from '$lib/SideScrollMenu.svelte';
    import Title from '$lib/Title.svelte';
    import SearchBar from '$lib/SearchBar.svelte';
    import '../styles/global.css';

    let query = "";
</script>

<Title></Title>
<SearchBar></SearchBar>
<main>
    <div class="left-bar">
        <SideScrollMenu></SideScrollMenu>
    </div>
    <div class="center">
        <div class="search">
            <SearchResult query = {query}></SearchResult>
        </div>
        <div class="search">
            <SearchResult query = {query}></SearchResult>
        </div>
    </div>
</main>

<style>
    main {
        display: flex;
    }
    .left-bar {
        height: 100vh;
        width: 200px;
        display: inline-block;
    }
    .center {
        display: flex;
        flex-direction: row;
        width: 100%;
    }
    .search {
        width: 50%;
        height: 100vh;
        overflow-y: auto;
    }
</style>