<script>
    import "../app.css";
    import { AppBar } from '@skeletonlabs/skeleton';
    import { searchQuery } from './store.js';
</script>

<AppBar class="custom-appbar">
    <svelte:fragment slot="lead">
        <a href="/">
            <img src="/logo.png" alt="Your Icon" class="h-16 w-16 ml-8"/>
        </a>
    </svelte:fragment>
    <input type="text" bind:value={$searchQuery} placeholder="Search" class="h-10 px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent mx-auto" />
    <svelte:fragment slot="trail">
        <a href="/add">
            <img src="/add.svg" alt="Your Icon" class="h-16 w-16"/>
        </a>
        <a href="/user">
            <img src="/user.svg" alt="Your Icon" class="h-16 w-16 mr-8"/>
        </a>
    </svelte:fragment>
</AppBar>
<slot></slot>