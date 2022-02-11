import Link from "next/link";
import { Card, Grid, Icon, Image } from "semantic-ui-react";

export default function FeedList({ list }: any) {
  function count(list: any) {
    console.log(list);
    if (list.length == 0) return 0;
    return list.length;
  }
  console.log(list);
  return (
    <>
      <Grid columns={3} stackable container>
        <Grid.Row>
          {list.map((item: any) => (
            <Grid.Column key={item.id}>
              <Link href={`/feed/personal/${item.id}`} className="content">
                <Card>
                  <Image src={item.filePath[0]} alt={item.filePath[0]} />
                  <Card.Content>
                    <Image floated="left" size="mini" src={item.filePath[0]} />
                    <Card.Header>{item.content}</Card.Header>
                    <Card.Meta>
                      <span>{item.modifiedDate}</span>
                    </Card.Meta>
                    <Card.Description>
                      Steve wants to add you to the group{" "}
                      <strong>best friends</strong>
                    </Card.Description>
                  </Card.Content>
                  <Card.Content extra textAlign="center">
                    <span>
                      {item.likes}&nbsp;&nbsp;
                      <Icon name="like" />
                    </span>
                    <span>
                      #고쳐라#{count(item.comments)}&nbsp;&nbsp;
                      <Icon name="comments" />
                    </span>
                  </Card.Content>
                </Card>
                {/* <a>
                  <Image src={item.filePath[0]} alt={item.filePath[0]} />
                  <p className="content">{item.content}</p>
                </a> */}
              </Link>
            </Grid.Column>
          ))}
        </Grid.Row>
      </Grid>
      <style jsx>{`
        .content {
          padding: 50px 5px 5px 5px;
          overflow: hidden;
          text-overflow: ellipsis;
        }
      `}</style>
    </>
  );
}
