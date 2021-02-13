// Fetch an object from JAVA REST API
export enum METHODS {
	POST = 'post',
	GET = 'get',
	DELETE = 'delete',
}

export default function fetchApi<T>(url: string, method: METHODS, body?: any): Promise<T> {
	console.log(`Call to fetch with ${url} mnethod : ${method}`);
	return fetch(url, {
		method: method,
		cache: 'no-cache',
		credentials: 'omit',
		headers: {
			'Content-Type': 'application/json',
		},
		body: body !== undefined?JSON.stringify(body):""
	}).then(response => {
		if (!response.ok) {
			throw new Error(response.statusText);
		}
		return response.json();
	});
}
