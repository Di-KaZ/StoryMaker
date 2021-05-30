import Cookies from "js-cookie";
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
      position: "bottom-left",
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
      position: "bottom-left",
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
      position: "bottom-left",
    });
  }

  /**
   * Post Get or Delete from JAVA
   * @param url url to fetch from JAVA
   * @param method get post or delete
   * @param body data to send via post
   * @returns server response
   */
  public fetch<T>(
    url: string,
    method: METHODS,
    { urlparams, body }: { urlparams?: any; body?: any } = {}
  ): Promise<T> {
    let urlhttp = BaseStoryComponent.BASE_API_URL + url;
    let headers: any = {
      "Content-Type": "application/json",
    };
    // Si les url params ne sont pas vide on les ajoute a l'url
    if (urlparams !== undefined) {
      urlhttp += "?" + new URLSearchParams(urlparams);
    }

    const token = Cookies.get("token");
    if (token !== undefined) {
      headers = { ...headers, Authorization: token };
    }

    // console.log(body);
    // console.log(body.story);

    if (method === METHODS.POST) {
      return this.post(urlhttp, headers, body);
    }

    return this.getOrDelete(urlhttp, headers, method);
  }

  /**
   * Post data to Java api
   * @param url url to fetch
   * @param body object to send (DTOS)
   * @returns response of the server
   */
  private async post<T>(url: string, headers: any, body?: any): Promise<T> {
    const response = await fetch(url, {
      method: METHODS.POST,
      headers,
      body: JSON.stringify(body),
    });
    const jsonToDto: T = await response.json();
    console.log(jsonToDto);
    return jsonToDto;
  }

  /**
   * Delete of Get from JAVA api
   * @param url url to fetch
   * @param method get or delete
   * @returns reponse of server
   */
  private async getOrDelete<T>(
    url: string,
    headers: any,
    method: METHODS
  ): Promise<T> {
    const response = await fetch(url, {
      headers,
      method: method,
    });
    // console.log(Cookies.get("token"));
    // console.log(response.json());
    const jsonToDto: T = await response.json();
    return jsonToDto;
  }

}
