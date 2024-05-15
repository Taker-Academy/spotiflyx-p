<script>
    import "../../../app.css";
</script>

<main class="flex justify-center items-center h-full">
    <div class="glass-form">
        <div class="w-full">
            <h2 class="text-7xl mb-12">Add content</h2>
            <p>Type</p>
            <select class="select mb-4 pl-3">
                <option value="1">Youtube</option>
                <option value="2">Spotify</option>
            </select>  
            <p>Name</p>   
            <input class="input mb-4 pl-3" title="Name" type="text" placeholder="Name" />
            <p>Link</p>
            <input class="input mb-4 pl-3" title="Link" type="url" placeholder="https://youtu.be/xvFZjo5PgG0?si=39IIZwcvdokOdE3R" />

        </div>
        <div class="flex flex-col">
            <button type="button" class="btn variant-filled m-4">Add</button>
        </div>
    </div>
</main>