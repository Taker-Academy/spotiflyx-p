/** @type {import('./$types').PageLoad} */
export async function load() {
    const token = localStorage.getItem('token');
    if (!token) {
        return { status: 401 };
    }
    const options = {
        headers: {
            Authorization: `Bearer ${token}`
        }
    };

    const url = `http://127.0.0.1:7070/auth/account`;
	const response = await fetch(url, options);

    return response.json();
}