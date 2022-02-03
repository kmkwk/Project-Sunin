import Link from "next/link";
import { Grid, Image } from "semantic-ui-react";

export default function FeedList({ list }: any) {
  return (
    <>
      <Grid columns={3} stackable>
        <Grid.Row>
          {list.map((item: any) => (
            <Grid.Column key={item.id}>
              <Link href={`/feed/personal/${item.id}`}>
                <a>
                  <Image src={item.filePath[0]} alt={item.filePath[0]} />
                  <p className="content">{item.content}</p>
                </a>
              </Link>
            </Grid.Column>
          ))}
        </Grid.Row>
      </Grid>
      <style jsx>{`
        .content {
          overflow: hidden;
          text-overflow: ellipsis;
        }
      `}</style>
    </>
  );
}
