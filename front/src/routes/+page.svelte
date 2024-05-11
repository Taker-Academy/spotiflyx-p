<script type="ts">
    import "../app.css";
    import { TabGroup, Tab, TabAnchor } from '@skeletonlabs/skeleton';
    import { searchQuery } from './store.js';

    let tabSet = 0;
    let searchValue = '';

    searchQuery.subscribe(value => {
        searchValue = value;
    });
</script>


<TabGroup
    justify="justify-center"
	active="variant-filled-primary"
	hover="hover:variant-soft-primary"
	flex="flex-1 lg:flex-none"
	rounded=""
	class="bg-surface-100-800-token w-full"
>
	<Tab bind:group={tabSet} name="tab1" value={0}>
        <img src="/youtube.svg" alt="YouTube" class="search-icon"/>
    </Tab>
	<Tab bind:group={tabSet} name="tab2" value={1}>
        <img src="/spotify.svg" alt="YouTube" class="search-icon"/>
    </Tab>
	<svelte:fragment slot="panel">
		{#if tabSet === 0}
			<p>{searchValue}</p>
		{:else if tabSet === 1}
            <p>{searchValue}</p>
		{/if}
	</svelte:fragment>
</TabGroup>
			