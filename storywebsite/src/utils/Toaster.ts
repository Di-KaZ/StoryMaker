import { useToast } from 'primevue/usetoast';

export default class Toaster {
	/**
	 * Hooks into Toast of PrimeVue
	 */
<<<<<<< HEAD
	// private static _toast = null;

	// /**
	//  * Dsiplay the error message passed in parameter in a Toast
	//  * @param errorMsg message to display in Toast
	//  */
	// public static error(errorMsg: string): void {
	// 	this._toast.add({
	// 		severity: 'error',
	// 		summary: 'Une erreur est intervenue.',
	// 		detail: errorMsg,
	// 		life: 3000,
	// 	});
	// }

	// /**
	//  * Dsiplay the info message passed in parameter in a Toast
	//  * @param infoMsg message to display in Toast
	//  */
	// public static info(infoMsg: string): void {
	// 	this._toast.add({
	// 		severity: 'info',
	// 		summary: 'Une erreur est intervenue.',
	// 		detail: infoMsg,
	// 		life: 3000,
	// 	});
	// }

	// /**
	//  * Dsiplay the info message passed in parameter in a Toast
	//  * @param successMsg message to display in Toast
	//  */
	// public static sucess(successMsg: string): void {
	// 	this._toast.add({
	// 		severity: 'info',
	// 		summary: 'Une erreur est intervenue.',
	// 		detail: successMsg,
	// 		life: 3000,
	// 	});
	// }
=======
	private _toast = useToast();

	/**
	 * Dsiplay the error message passed in parameter in a Toast
	 * @param errorMsg message to display in Toast
	 */
	public error(errorMsg: string, detail?: string): void {
		this._toast.add({
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
	public info(infoMsg: string, detail?: string): void {
		this._toast.add({
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
	public sucess(successMsg: string, detail?: string): void {
		this._toast.add({
			severity: 'info',
			summary: successMsg,
			detail: detail,
			life: 3000,
		});
	}
>>>>>>> main
}
