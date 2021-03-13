import { useToast } from 'primevue/usetoast';
import { Vue } from 'vue-class-component';

// Fetch an object from JAVA REST API
export enum METHODS {
	POST = 'post',
	GET = 'get',
	DELETE = 'delete',
}

/**
 * Base class that have all function used to toast and fetch from java api
 */
export default class BaseStoryComponent extends Vue {
	private static BASE_API_URL = 'http://localhost:8080/';
	private toast = useToast();
	/**
	 * Dsiplay the error message passed in parameter in a Toast
	 * @param errorMsg message to display in Toast
	 */
	public errorToast(errorMsg: string, detail?: string): void {
		this.toast.add({
			severity: 'error',
			summary: errorMsg,
			detail: detail,
			life: 3000,
		});
	}

	/**
	 * Dsiplay the info message passed in parameter in a Toast
	 * @param infoMsg message to display in Toast
	 */
	public infoToast(infoMsg: string, detail?: string): void {
		this.toast.add({
			severity: 'info',
			summary: infoMsg,
			detail: detail,
			life: 3000,
		});
	}

	/**
	 * Dsiplay the info message passed in parameter in a Toast
	 * @param successMsg message to display in Toast
	 */
	public sucessToast(successMsg: string, detail?: string): void {
		this.toast.add({
			severity: 'info',
			summary: successMsg,
			detail: detail,
			life: 3000,
		});
	}

	/**
	 * Post Get or Delete from JAVA
	 * @param url url to fetch from JAVA
	 * @param method get post or delete
	 * @param body data to send via post
	 * @returns server response (DTO)
	 */
	// eslint-disable-next-line
	public fetch<T>(url: string, method: METHODS, body?: any): Promise<T> {
		if (body !== undefined) {
			return this.post(BaseStoryComponent.BASE_API_URL + url, body);
		}
		return this.getOrDelete(BaseStoryComponent.BASE_API_URL + url, method);
	}

	/**
	 * Post data to Java api
	 * @param url url to fetch
	 * @param body object to send (DTOS)
	 * @returns response of the server
	 */
	// eslint-disable-next-line
	private async post<T>(url: string, body?: any): Promise<T> {
		const response = await fetch(url, {
			method: METHODS.POST,
			cache: 'no-cache',
			credentials: 'omit',
			headers: {
				'Content-Type': 'application/json',
			},
			body: JSON.stringify(body),
		});
		if (!response.ok) {
			throw new Error(response.statusText);
		}
		const jsonToDto: T = await response.json();
		return jsonToDto;
	}

	/**
	 * Delete of Get from JAVA api
	 * @param url url to fetch
	 * @param method get or delete
	 * @returns reponse of server
	 */
	private async getOrDelete<T>(url: string, method: METHODS): Promise<T> {
		const response = await fetch(url, {
			method: method,
			cache: 'no-cache',
			credentials: 'omit',
			headers: {
				'Content-Type': 'application/json',
			},
		});
		if (!response.ok) {
			throw new Error(response.statusText);
		}
		const jsonToDto: T = await response.json();
		return jsonToDto;
	}
}
