<script>
    import { goto } from "$app/navigation";
    import "../../../app.css";

    export let data;

    let firstName = data.data.firstName;
    let lastName = data.data.lastName;
    let email = data.data.email;
    let password = data.data.password;

    function disconnect() {
        localStorage.removeItem('token');
        goto("/login");
    }

</script>

<main>
    {#if data.ok}
        <h1 class="text-9xl mx-auto">Hello {firstName} {lastName}</h1>
        <button on:click={disconnect}>Disconnect</button>
    {/if}
</main>