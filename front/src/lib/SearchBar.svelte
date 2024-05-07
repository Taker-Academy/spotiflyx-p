<script>
    import { createEventDispatcher } from 'svelte';
    const dispatch = createEventDispatcher();
  
    let search = '';
  
    function updateSearch() {
      dispatch('search', search);
    }
</script>
  
  
<div class="search-bar">
    <input type="text" bind:value={search} on:input={updateSearch} />
    <button class="search-button">Search</button>
</div>

<style>
    .search-bar {
        display: flex;
        justify-content: center;
        align-items: center;
        height: 40px;
        width: 300px;
        margin: 0 auto;
        background-color: #f2f2f2;
        border-radius: 20px;
        padding: 0 10px;
    }

    .search-input {
        flex: 1;
        border: none;
        outline: none;
        background-color: transparent;
        font-size: 16px;
    }

    .search-button {
        border: none;
        outline: none;
        background-color: transparent;
        cursor: pointer;
        margin-left: 10px;
    }
</style>
