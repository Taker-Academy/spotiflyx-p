<script>
    import "../../app.css";
    import { AppBar } from '@skeletonlabs/skeleton';
    import { searchQuery } from './store.js';
</script>

<AppBar class="custom-appbar">
    <svelte:fragment slot="lead">
        <a href="/">
            <img src="/logo.png" alt="Your Icon" class="h-16 w-16 ml-8"/>
        </a>
    </svelte:fragment>
    <div class="input-group input-group-divider grid-cols-[auto_1fr_auto] h-12">
        <div class="input-group-shim"><img src="/search_icon.svg" alt="Search Icon" class=""/></div>
        <input type="search" bind:value={$searchQuery} placeholder="Search..." class="pl-1" />
        <button class="variant-filled-secondary">Submit</button>
    </div>
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