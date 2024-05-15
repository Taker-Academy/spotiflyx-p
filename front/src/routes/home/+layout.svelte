<script>
    import { goto } from "$app/navigation";
    import "../../app.css";
    import { AppBar } from '@skeletonlabs/skeleton';
    import { error } from '@sveltejs/kit';
    import { onMount } from 'svelte';

    let token;

    onMount(async () => {
        token = localStorage.getItem('token');
        if (!token) {
            goto('/');
            return;
        }
        const options = {
            headers: {
                Authorization: `Bearer ${token}`
            }
        };

        const url = `http://127.0.0.1:7070/auth/account`;
        const response = await fetch(url, options);

        if (!response.ok) {
            goto('/');
        }
    });
</script>

<AppBar class="custom-appbar">
    <svelte:fragment slot="lead">
        <a href="/home">
            <img src="/logo.png" alt="Spotiflix logo" class="big-icon ml-8"/>
        </a>
    </svelte:fragment>
    <svelte:fragment slot="trail">
        <a href="/home/add">
            <img src="/add.svg" alt="add" class="big-icon"/>
        </a>
        <a href="/home/user">
            <img src="/user.svg" alt="user" class="big-icon mr-8"/>
        </a>
    </svelte:fragment>
</AppBar>
<slot></slot>