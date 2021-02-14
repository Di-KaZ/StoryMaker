// Fetch an object from JAVA REST API
export enum METHODS {
	POST = 'post',
	GET = 'get',
	DELETE = 'delete',
}

export default async function fetchApi<T>(url: string, method: METHODS, body?: any): Promise<T> {
	console.log(`Call to fetch with ${url} mnethod : ${method}`);
	const response = await fetch(url, {
		method: method,
		cache: 'no-cache',
		credentials: 'omit',
		headers: {
			'Content-Type': 'application/json',
		},
		body: body !== undefined ? JSON.stringify(body) : '',
	});
	if (!response.ok) {
		throw new Error(response.statusText);
	}
	return await response.json();
}
