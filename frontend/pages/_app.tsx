import "../styles/globals.css";
import "semantic-ui-css/semantic.min.css";

import type { AppProps } from "next/app";
import Head from "next/head";

function MyApp({ Component, pageProps }: AppProps) {
  return (
    <>
      <Head>
        <title>Sun-In</title>
        <link rel="shortcut icon" href="/images/로고만.png" />
      </Head>
      <Component {...pageProps} />
    </>
  );
}

export default MyApp;
