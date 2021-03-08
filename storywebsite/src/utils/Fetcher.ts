import Toaster from './Toaster';

// Fetch an object from JAVA REST API
export enum METHODS {
	POST = 'post',
	GET = 'get',
	DELETE = 'delete',
}

export default class Fetcher {
	/**
	 * Post Get or Delete from JAVA
	 * @param url url to fetch from JAVA
	 * @param method get post or delete
	 * @param body data to send via post
	 * @returns server response (DTO)
	 */
	public static fetch<T>(url: string, method: METHODS, body?: any): Promise<T> {
		if (body !== undefined) {
			return Fetcher.post(url, body);
		}
		return Fetcher.getOrDelete(url, method);
	}

	/**
	 * Post data to Java api
	 * @param url url to fetch
	 * @param body object to send (DTOS)
	 * @returns response of the server
	 */
	private static async post<T>(url: string, body?: any): Promise<T> {
		let response = await fetch(url, {
			method: METHODS.POST,
			cache: 'no-cache',
			credentials: 'omit',
			headers: {
				'Content-Type': 'application/json',
			},
			body: JSON.stringify(body),
		});
		if (!response.ok) {
			Toaster.error(response.statusText);
			throw new Error(response.statusText);
		}
		let jsonToDto: T = await response.json();
		return jsonToDto;
	}

	/**
	 * Delete of Get from JAVA api
	 * @param url url to fetch
	 * @param method get or delete
	 * @returns reponse of server
	 */
	private static async getOrDelete<T>(url: string, method: METHODS): Promise<T> {
		let response = await fetch(url, {
			method: METHODS.POST,
			cache: 'no-cache',
			credentials: 'omit',
			headers: {
				'Content-Type': 'application/json',
			},
		});
		if (!response.ok) {
			Toaster.error(response.statusText);
			throw new Error(response.statusText);
		}
		let jsonToDto: T = await response.json();
		return jsonToDto;
	}
}
