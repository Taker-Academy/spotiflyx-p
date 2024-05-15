<script lang="ts">
    import { goto } from "$app/navigation";
    import "../app.css";
    import { onMount } from 'svelte';

    export let tabSet: number;

    let youtubeData: any[] = [];
    let spotifyData: any[] = [];

    onMount(async () => {
        const options = {
            method: 'GET',
            headers: {'Content-Type': 'application/json', 'User-Agent': 'insomnia/9.0.0'}
        };

        const response = await fetch('http://127.0.0.1:7070/content', options)
        let data = await response.json();

        youtubeData = data.youtube;
        spotifyData = data.spotify;
        console.log(data);
    });

    function gotoContentPage(videoId: string, contentType: string) {
        console.log(videoId);
        if (contentType === 'youtube') {
            goto(`/home/youtube/${videoId}`);
        } else {
            goto(`/home/spotify/${videoId}`);
        }
    }
</script>

<div class="max-h-screen overflow-x-auto h-full min-h-screen  pb-48">
    {#if tabSet === 0}
        {#if youtubeData.length !== 0}
            {#each youtubeData as item (item.id)}
                <div class="block card card-hover p-4 m-4 mx-auto h-80 w-96 bg-surface-400" 
                    role="button" 
                    tabindex="0"
                    on:click={gotoContentPage(item.api_id, 'youtube')}
                    on:keydown={gotoContentPage(item.api_id, 'youtube')}
                >
                    <img src={item.image_url} alt="video thumbnail" class="w-full rounded-lg h-3/4">
                    <p>{item.title}</p>
                    <p>{item.artist}</p>
                </div>
            {/each}
        {/if}
    {:else}
        {#if spotifyData.length !== 0}
            {#each spotifyData as item (item.id)}
                <div class="block card card-hover p-4 m-4 mx-auto h-80 w-96 bg-surface-400" 
                    role="button" 
                    tabindex="0"
                    on:click={gotoContentPage(item.api_id, 'spotify')}
                    on:keydown={gotoContentPage(item.api_id, 'spotify')}
                >
                    <img src={item.image_url} alt="video thumbnail" class="w-full rounded-lg h-3/4">
                    <p>{item.title}</p>
                    <p>{item.artist}</p>
                </div>
            {/each}
        {/if}
    {/if}
</div>
