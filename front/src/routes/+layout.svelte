<script>
    import "../app.css";
    import { AppBar } from '@skeletonlabs/skeleton';
</script>

<AppBar class="custom-appbar" gridColumns="grid-cols-3" slotDefault="place-self-center" slotTrail="place-content-end">
    <svelte:fragment slot="lead">
        <img src="/logo.png" alt="Your Icon" class="h-16 w-16"/>
    </svelte:fragment>
    <p class=" text-primary-600 ">Spotiflix</p>
    <svelte:fragment slot="trail">
        <img src="/user.png" alt="Your Icon" class="h-16 w-16"/>
    </svelte:fragment>
</AppBar>
<slot></slot>