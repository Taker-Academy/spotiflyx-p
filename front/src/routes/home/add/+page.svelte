<script lang="ts">
    import "../../../app.css";
    import { getToastStore } from '@skeletonlabs/skeleton';
    import type { ToastSettings } from '@skeletonlabs/skeleton';

    let content = {
        contentType: "",
        title: "",
    };

    const toastStore = getToastStore();
	
    const successToast: ToastSettings = {
        message: 'Uploaded.',
        background: 'variant-filled-success',
    };

    const errorToast: ToastSettings = {
        message: 'Failed to upload.',
        background: 'variant-filled-error',
    };

    async function uploadContent() {
        const token = localStorage.getItem('token');
        const options = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(content)
        };

        console.log(options.body);

        const url = `http://127.0.0.1:7070/content/create`;
        const response = await fetch(url, options);

        if (response.ok) {
            toastStore.trigger(successToast);
        } else {
            toastStore.trigger(errorToast);
        }
    }
</script>

<main class="flex justify-center items-center h-full">
    <div class="glass-form">
        <div class="w-full">
            <h2 class="text-7xl mb-12">Add content</h2>
            <p>Type</p>
            <select bind:value={content.contentType} class="select mb-4 pl-3">
                <option value="YOUTUBE">Youtube</option>
                <option value="SPOTIFY">Spotify</option>
            </select>  
            <p>Name</p>   
            <input class="input mb-4 pl-3" bind:value={content.title} title="Name" type="text" placeholder="Name" />

        </div>
        <div class="flex flex-col">
            <button type="button" on:click={uploadContent} class="btn variant-filled m-4">Add</button>
        </div>
    </div>
</main>