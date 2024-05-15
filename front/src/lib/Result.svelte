<script>
    import "../app.css";

    export let searchValue = '';
    let range = Array.from({length: 20}, (_, i) => i);
</script>

<div class="max-h-screen overflow-x-auto h-full min-h-screen  pb-48">
    {#each range as number}
        <div class="block card card-hover p-4 m-4 mx-auto h-80 w-96 bg-surface-400">
            <img src="https://img.youtube.com/vi/GtffucAmM78/maxresdefault.jpg" alt="video thumbnail" class="w-full rounded-lg">
            <p>{searchValue}</p>
        </div>
    {/each}
</div>

<style>

</style>