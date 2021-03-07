import { useToast } from 'primevue/usetoast';

// Fetch an object from JAVA REST API
export enum METHODS {
	POST = 'post',
	GET = 'get',
	DELETE = 'delete',
}

/**
 * fatch Json data to DTO from java spring api
 * @param url url to fetch from java api
 * @param method method of fetch
 * @param body the data to send
 * @returns T
 */
export default async function fetchApi<T>(url: string, method: METHODS, body?: any): Promise<T> {
	console.log(`Call to fetch with ${url} method : ${method}`);
	const toast = useToast();
	if (body !== undefined) {
		const response = await fetch(url, {
			method: method,
			cache: 'no-cache',
			credentials: 'omit',
			headers: {
				'Access-Control-Allow-Origin': '*',
				'Content-Type': 'application/json',
			},
			body: JSON.stringify(body),
		}).then(response => {
			if (!response.ok) {
				toast.add({ severity: 'error', summary: 'Une erreur est intervenue.', detail: 'Test', life: 3000 });
				throw new Error(response.statusText);
			}
			return response.json();
		});
	}
	const response = await fetch(url, {
		method: method,
		cache: 'no-cache',
		credentials: 'omit',
		headers: {
			'Content-Type': 'application/json',
		},
	});
	if (!response.ok) {
		toast.add({ severity: 'error', summary: 'Une erreur est intervenue.', detail: 'Test', life: 3000 });
		throw new Error(response.statusText);
	}
	return response.json();
}
