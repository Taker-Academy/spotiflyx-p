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
        message: 'Error when registering user.',
        background: 'variant-filled-error',
    };

    let user = {
        firstName: "",
        lastName: "",
        email: "",
        password: ""
    }

    async function register() {
        const userData = JSON.stringify(user);
        const response = await fetch('http://127.0.0.1:7070/auth/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: userData
        });

        if (response.ok) {
            console.log('User registered successfully');
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
            <h2 class="text-7xl mb-12">Register</h2>
            <input class="input mb-4 pl-3" bind:value={user.firstName} title="Input (text)" type="text" placeholder="First Name" />
            <input class="input mb-4 pl-3" bind:value={user.lastName} title="Input (text)" type="text" placeholder="Last Name" />
            <input class="input mb-4 pl-3" bind:value={user.email} title="Input (email)" type="email" placeholder="john@example.com" autocomplete="email" />
            <input class="input mb-4 pl-3" bind:value={user.password} title="Input (password)" type="password" placeholder="password" />
        </div>
        <div class="flex flex-col">
            <button type="button" on:click={register} class="btn variant-filled m-4">Register</button>
            <p class="mx-auto">Already have an account ? <a href="/login">Login</a></p>
        </div>
    </div>
</main>