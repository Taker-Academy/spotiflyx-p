<script>
    export let siteLogo = "/logo.png";
    export let title = "Spotiflix";
    export let userLogo = "/user.png";
</script>

<style>
    .title-container {
        display: flex;
        align-items: center;
        justify-content: space-between;
        padding: 10px;
    }

    .logo {
        width: 50px;
        height: 50px;
    }

    .title {
        font-size: 24px;
        font-weight: bold;
    }
</style>

<div class="title-container">
    <a href="/">
        <img class="logo" src={siteLogo} alt="Site Logo" />
    </a>
    <h1 class="title">{title}</h1>
    <img class="logo" src={userLogo} alt="User Logo" />
</div>