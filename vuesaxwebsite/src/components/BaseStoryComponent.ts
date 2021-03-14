import { Component, Vue } from "vue-property-decorator";
// Fetch an object from JAVA REST API
export enum METHODS {
  POST = "post",
  GET = "get",
  DELETE = "delete",
}

/**
 * Base class that have all function used to toast and fetch from java api
 */
@Component({})
export default class BaseStoryComponent extends Vue {
  declare $vs: any;
  private static BASE_API_URL = "http://localhost:8080/";
  /**
   * Dsiplay the error message passed in parameter in a Toast
   * @param errorMsg message to display in Toast
   */
  public errorToast(errorMsg: string, detail?: string): void {
    this.$vs.notify({
      title: errorMsg,
      text: detail,
      color: "danger",
      icon: "error",
    });
  }

  /**
   * Dsiplay the info message passed in parameter in a Toast
   * @param infoMsg message to display in Toast
   */
  public infoToast(infoMsg: string, detail?: string): void {
    this.$vs.notify({
      title: infoMsg,
      text: detail,
      color: "primary",
      icon: "help",
    });
  }

  /**
   * Dsiplay the info message passed in parameter in a Toast
   * @param successMsg message to display in Toast
   */
  public successToast(successMsg: string, detail?: string): void {
    this.$vs.notify({
      title: successMsg,
      text: detail,
      color: "success",
      icon: "done",
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
  public fetch<T>(
    url: string,
    method: METHODS,
    params?: any,
    body?: any
  ): Promise<T> {
    let urlhttp = BaseStoryComponent.BASE_API_URL + url;
    if (params !== undefined) {
      urlhttp += new URLSearchParams(params);
    }
    if (body !== undefined) {
      return this.post(urlhttp, body);
    }
    return this.getOrDelete(urlhttp, method);
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
      credentials: "omit",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(body),
    });
    if (!response.ok) {
      this.errorToast(
        "Error when fetching data",
        "[" + METHODS.POST.toUpperCase() + "] " + response.status.toString()
      );
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
      credentials: "omit",
      headers: {
        "Content-Type": "application/json",
      },
    });
    if (!response.ok) {
      this.errorToast(
        "Error when fetching data",
        "[" + method.toUpperCase() + "] " + response.status.toString()
      );
    }
    const jsonToDto: T = await response.json();
    return jsonToDto;
  }
}
