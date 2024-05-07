<script>
    let menuItems = [
        "Item 1",
        "Item 2",
        "Item 3",
        "Item 4",
        "Item 5",
        "Item 6",
        "Item 7",
        "Item 8",
        "Item 9",
        "Item 10",
        "Item 11",
        "Item 12",
        "Item 13",
        "Item 14",
        "Item 15",
        "Item 16",
        "Item 17",
        "Item 18",
        "Item 19",
        "Item 20",
        "Item 21",
        "Item 22",
        "Item 23",
        "Item 24",
        "Item 25",
        "Item 26",
        "Item 27",
        "Item 28",
        "Item 29",
        "Item 30",
        "Item 31",
        "Item 32",
        "Item 33",
        "Item 34",
        "Item 35",
        "Item 36",
        "Item 37",
        "Item 38",
        "Item 39",
        "Item 40"
    ];
    export let query = "";
</script>
  
<div class="result">
    {#each menuItems as item}
        {#if query == ""}
            <div class="item">{item}</div>
        {:else if item == query}
            <div class="item">{item}</div>
        {/if}
    {/each}
</div>

<style>
    .result {
        position: fixed;
        height: 100%;
        width: 100%;
        overflow-y: auto;
        border: 1px solid #ccc;
    }
  
    .item {
        height: 100px;
        padding: 5px;
        border-bottom: 1px solid #ccc;
        background-color: #555;
    }
  
    .item:hover {
        background-color: #f0f0f0;
    }
  
    .result::-webkit-scrollbar {
        width: 8px;
    }
  
    .result::-webkit-scrollbar-track {
        background-color: #f1f1f1;
    }
  
    .result::-webkit-scrollbar-thumb {
        background-color: #888;
        border-radius: 4px;
    }
  
    .result::-webkit-scrollbar-thumb:hover {
        background-color: #555;
    }
</style>