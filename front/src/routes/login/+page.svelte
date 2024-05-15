<script lang="ts">
    import { goto } from "$app/navigation";
    import "../../app.css";
    import { getToastStore } from '@skeletonlabs/skeleton';
    import type { ToastSettings } from '@skeletonlabs/skeleton';

    const toastStore = getToastStore();

    const welcomeToast: ToastSettings = {
        message: 'Welcome to the app.',
        background: 'variant-filled-success',
    };

    const errorToast: ToastSettings = {
        message: 'Error when login in.',
        background: 'variant-filled-error',
    };

    let user = {
        email: "",
        password: ""
    }

    async function login() {
        const userData = JSON.stringify(user);
        const response = await fetch('http://127.0.0.1:7070/auth/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: userData
        });

        if (response.ok) {
            console.log('User login successfully');
            toastStore.trigger(welcomeToast);
            goto("/home");
        } else {
            console.error('Registration failed');
            toastStore.trigger(errorToast);
        }

        response.json().then(data => {
            const token = data.data.token;
            localStorage.setItem('token', token);
        });
    }
</script>

<main class="flex justify-center items-center h-full">
    <div class="glass-form">
        <div class="w-full">
            <h2 class="text-7xl mb-12">Login</h2>
            <input class="input mb-4 pl-3" bind:value={user.email} title="Input (email)" type="email" placeholder="john@example.com" autocomplete="email" />
            <input class="input mb-4 pl-3" bind:value={user.password} title="Input (password)" type="password" placeholder="password" />
        </div>
        <div class="flex flex-col">
            <button type="button" on:click={login} class="btn variant-filled m-4">Login</button>
            <p class="mx-auto">Don't have an account ? <a href="/register">Register</a></p>
        </div>
    </div>
</main>